package io.github.kunal26das.kisan_network.contact.list

import android.Manifest.permission.READ_CONTACTS
import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI
import android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER
import android.provider.ContactsContract.Contacts.DISPLAY_NAME
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.kunal26das.kisan_network.contact.Contact
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.Closeable

class ContactListPagingSource(
    @ApplicationContext applicationContext: Context
) : PagingSource<Int, Contact>(), Closeable {

    private val permissionGranted = ContextCompat.checkSelfPermission(
        applicationContext, READ_CONTACTS
    ) == PERMISSION_GRANTED

    private val cursor = when {
        permissionGranted -> applicationContext.contentResolver.query(
            CONTENT_URI,
            arrayOf(DISPLAY_NAME, NUMBER),
            null,
            null,
            "$DISPLAY_NAME ASC"
        )
        else -> null
    }

    private val displayName
        @SuppressLint("Range")
        get() = cursor?.getString(cursor.getColumnIndex(DISPLAY_NAME)) ?: ""

    private val number
        @SuppressLint("Range")
        get() = cursor?.getString(cursor.getColumnIndex(NUMBER))?.filter { it.isDigit() } ?: ""

    private val countryCode
        get() = try {
            number.split(phoneNumber)[0]
        } catch (e: Throwable) {
            ""
        }

    private val phoneNumber
        get() = number.takeLast(10)

    private val contact
        get() = Contact(displayName, phoneNumber, countryCode)

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Contact> {
        val contacts = mutableListOf<Contact>()
        val key = params.key ?: 0
        repeat(params.loadSize) {
            if (cursor?.moveToNext() == true) {
                contacts.add(contact)
            }
        }
        val distinctContacts = contacts.distinctBy { it.phoneNumber }
        return LoadResult.Page(
            data = distinctContacts,
            prevKey = null,
            nextKey = if (contacts.isNotEmpty()) key + 1 else null,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Contact>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun close() {
        cursor?.close()
    }
}