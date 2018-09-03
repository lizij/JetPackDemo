package work

import androidx.work.Data
import androidx.work.Worker
import java.text.DateFormat

class MyWorker : Worker() {
    private val TAG = javaClass.simpleName

    override fun doWork(): Result {
        // get input data
        val name = inputData.getString(COMPONENT_NAME)
        val dateFormat = DateFormat.getDateTimeInstance()

        // set output data
        val msg = "%s is running at %s".format(name, dateFormat.format(System.currentTimeMillis()))
        outputData = Data.Builder()
                .putString(MESSAGE, msg)
                .build()

        return Result.SUCCESS
    }

    companion object {
        val COMPONENT_NAME = "component_name"
        val MESSAGE = "running_time"
    }
}