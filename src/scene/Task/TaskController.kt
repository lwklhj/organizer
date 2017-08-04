package scene.Task

import resources.database.entity.User

import scene.Task.entity.Task
import scene.event.entity.Event
import java.util.*
import kotlin.collections.ArrayList
import scene.Task.entity.getTaskByUser
import scene.Task.entity.deleteTask
/**
 * Created by hhf on 6/29/17.
 */
//save task to database
enum class Priority(val index:Int){
    NONE(0){
        override fun toStr(): String="None"
        override fun getColor(): String ="#3498db"

    },
    LOW(1){
        override fun toStr(): String="Low"
        override fun getColor(): String ="#2ecc71"
    },
    MEDIUM(2){
        override fun toStr(): String="Medium"
        override fun getColor(): String ="#f1c40f"
    },
    HIGH(3){
        override fun toStr(): String="High"
        override fun getColor(): String ="#e74c3c"
    };
    abstract fun toStr():String
    abstract fun getColor():String
}
enum class Repeat(val index:Int){
    NONE(0){
        override fun toStr(): String="None"
    },
    DAILY(1){
        override fun toStr(): String="Daily"
    },
    WEEKLY(2){
        override fun toStr(): String="Weekly"
    };
    abstract fun toStr():String
}
fun getMember(task:Task){

}
fun addTask(task_name:String, start: String, end: String, description:String, priorityInt: Int,repeatInt: Int, location:String,event:String,user:String){
    val task= Task(-1,task_name,
            convertStringToDate(start),
            convertStringToDate(end),
            description,
            intToPriority(priorityInt),
            intToRepeat(repeatInt),
            location,
            event,
            user)
    task.save()

}
fun getTaskByEvent(event: Event){


}
fun getTaskByUser(userId:String):ArrayList<Task>{
    return getTaskByUser(userId)

}
//datetime formal   :dd/mm/yyyy hh:mm
fun convertStringToDate(dateTime:String):Calendar{
    //[0] date [1]time
    val data=dateTime.split(" ");
    val dateString=data.get(0).split("/");
    val timeString=data.get(1).split(":")
    val date=Calendar.getInstance();
    date.set(Integer.parseInt(dateString.get(2)),
            Integer.parseInt(dateString.get(1))-1,
            Integer.parseInt(dateString.get(0)),
            Integer.parseInt(timeString.get(0)),
            Integer.parseInt(timeString.get(1)))
    date.set(Calendar.SECOND,0);
    return date
}
//yyyy/mm//dd hh:mm
fun convertStringToDateYearAtFront(dateTime:String):Calendar{
    //[0] date [1]time
    val data=dateTime.split(" ");
    val dateString=data.get(0).split("-");
    val timeString=data.get(1).split(":")
    val date=Calendar.getInstance();
    date.set(Integer.parseInt(dateString.get(0)),
            Integer.parseInt(dateString.get(1))-1,
            Integer.parseInt(dateString.get(2)),
            Integer.parseInt(timeString.get(0)),
            Integer.parseInt(timeString.get(1)))
    date.set(Calendar.SECOND,0);
    return date
}
fun convertDateToString(date:Calendar):String{
    val year=date.get(Calendar.YEAR)
    val month=date.get(Calendar.MONTH)+1;
    val day=date.get(Calendar.DATE)
    val hour=date.get(Calendar.HOUR_OF_DAY)
    val minutes=date.get(Calendar.MINUTE)
    return "$year/$month/$day $hour:$minutes"
}
fun convertDateToStringYearAtEnd(date:Calendar):String{
    val year=date.get(Calendar.YEAR)
    val month=date.get(Calendar.MONTH)+1;
    val day=date.get(Calendar.DATE)
    val hour=date.get(Calendar.HOUR_OF_DAY)
    val minutes=date.get(Calendar.MINUTE)
    return "$day/$month/$year $hour:$minutes"
}
fun calStringTime(cal:Calendar):String{
    val hour=cal.get(Calendar.HOUR_OF_DAY)
    val minutes=cal.get(Calendar.MINUTE)
    return "$hour:$minutes"
}

//priority
fun intToPriority(value:Int):Priority{
    when(value){
        1->return Priority.LOW
        2->return Priority.MEDIUM
        3->return Priority.HIGH
    }
    return Priority.NONE;
}
fun priorityToInt(priority: Priority)=priority.ordinal;

//repeat
fun repeatToInt(repeat: Repeat)=repeat.ordinal;
fun intToRepeat(value:Int):Repeat{
    when(value){
        1-> return Repeat.DAILY
        2->return Repeat.WEEKLY
    }
    return Repeat.NONE;

}

fun deleteTaskMain(t:Task){
    deleteTask(t);

}

