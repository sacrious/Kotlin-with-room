package com.sacrious.nativapps.common.di.modules

import android.arch.persistence.room.Room
import com.sacrious.nativapps.common.app.NativAppApplication
import com.sacrious.nativapps.storage.AppDatabase
import com.sacrious.nativapps.storage.DatabaseManager
import com.sacrious.nativapps.storage.DatabaseService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sacrious on 1/13/18.
 */
@Module
class AppCommonModule(nativAppApplication: NativAppApplication) {
  private var mContext: NativAppApplication = nativAppApplication

  /**
   * This method return the Application class used to inject in a singleton
   *
   * @return the Application class override for the project
   */
  @Provides
  @Singleton
  fun context(): NativAppApplication {
    return mContext
  }

  /**
   * This method return de Room database
   */
  @Provides
  @Singleton
  fun getAppDatabase(context: NativAppApplication): AppDatabase {
    return Room.databaseBuilder(context,
        AppDatabase::class.java,
        "nativapp_db").build()
  }

  /**
   * This method return the manager for handling database
   */
  @Provides
  @Singleton
  fun getDatabaseService(appDatabase: AppDatabase): DatabaseService {
    return DatabaseManager(appDatabase)
  }
}