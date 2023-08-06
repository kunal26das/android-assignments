package io.github.kunal26das.radius.init

import android.content.Context
import io.github.kunal26das.common.Initializer
import io.github.kunal26das.radius.database.work.ClearDatabaseWorker

class DatabaseInitializer : Initializer<Context>() {
    override fun create(context: Context): Context {
        ClearDatabaseWorker.enqueue(context)
        return context
    }
}