package scene.Task.entity

import resources.database.DB
import resources.database.entity.User
import scene.Task.Priority
import scene.Task.Repeat
import java.util.*
import scene.Task.convertDateToString;
import scene.Task.convertStringToDateYearAtFront
import scene.Task.intToPriority
import scene.Task.intToRepeat
import kotlin.collections.ArrayList

/**
 * Created by hhf on 6/28/17.
 */

fun getTaskByUser(userID:String):ArrayList<Task>{
    val result=DB.read("select * from task where userID='$userID'")
    val tastArr=ArrayList<Task>()
    while (result.next()){
        val id=result.getInt("id");
        val taskName:String?=result.getString("name")
        val start:Calendar= convertStringToDateYearAtFront(result.getString("start"))
        val end:Calendar= convertStringToDateYearAtFront(result.getString("end"))
        val desc:String?=result.getString("taskDesc")


        val repeat= intToRepeat(result.getInt("repeatType"))
        val priority= intToPriority(result.getInt("priority"))

        val location:String?=result.getString("location")
        val eventId:String?=result.getString("eventID")
        val userId=result.getString("userID")
        tastArr.add(Task(id,
                        taskName,
                        start,
                        end,
                        desc,
                        priority,
                        repeat,
                        location,
                        eventId,
                        userID))

    }
    //get from data base
    return tastArr


}
fun deleteTask(t:Task){
    val id=t.id
    DB.update("delete  from task where id=$id")

}

class Task(var id:Int,var task_name:String?, var start: Calendar, var end:Calendar, var description:String?, var priority: Priority, var repeat: Repeat, var location:String?, var event:String?, var creator:String):Comparable<Task>{
    fun save():Unit{
        val startDT= convertDateToString(start)
        val endDT= convertDateToString(end)
        val rep=repeat.ordinal;
        val pri=priority.ordinal;


        val query="INSERT INTO task(name,start,end,taskDesc,priority,repeatType,location,eventID,userID) VALUES("+
                                     "'$task_name','$startDT','$endDT','$description',$pri,$rep,'$location',1,'$creator')"

        DB.update(query)
    }

    override fun compareTo(other: Task): Int {
        if(other.start.after(start)) return -1;
        else if(other.start.before(start)) return 1;
        else{
            if(other.priority>priority) return -1;
            else if(other.priority<priority) return 1;
            else return 0;
        }
    }
}