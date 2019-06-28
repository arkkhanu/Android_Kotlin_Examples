package com.example.roomdb

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomdb.Database.Note
import com.example.roomdb.Database.NoteDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var notedb: NoteDatabase? = null
     var vlist : List<Note> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notedb = NoteDatabase.getInstance(this)!!

        btn_save.setOnClickListener {
            if (et_title.text.toString().isNotEmpty() && et_content.toString().isNotEmpty()) {
                val note_user = Note(0, et_title.text.toString(), et_content.text.toString())
                InsertNote(this, note_user).execute()

            } else {
                if (et_title.text.toString().isEmpty()) {
                    et_title.error = "Can't be Empty"
                }
                if (et_content.text.toString().isEmpty()) {
                    et_content.error = "Can't be Empty"
                }
//                Toast.makeText(this@MainActivity,"Cannot be empty" , Toast.LENGTH_SHORT).show()
            }
        }

        btn_show.setOnClickListener {
            Toast.makeText(this,"after ${vlist.size-1} ", Toast.LENGTH_SHORT).show()
            GetAllNote(this).execute()
        }

        btn_clear.setOnClickListener {
            vlist = emptyList()
            view_show.text =""
        }

    }

    private class InsertNote(val context: MainActivity, val note: Note) : AsyncTask<Unit, Unit, Boolean>() {

        override fun doInBackground(vararg p0: Unit?): Boolean {
            context.notedb!!.getNoteDao().insert(note)
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            if (result!!)
                Toast.makeText(context, "Inserted", Toast.LENGTH_SHORT).show()
        }
    }


     private class GetAllNote(var context: MainActivity) : AsyncTask<Unit, Unit, List<Note>>() {
        override fun doInBackground(vararg p0: Unit?): List<Note> {
//            context.vlist = emptyList()
            context.vlist = context.notedb!!.getNoteDao().getAll()
//           return context.notedb!!.getNoteDao().getAll()
            return context.vlist!!
        }

        override fun onPostExecute(result: List<Note>?) {

            if (result!!.size>0) {
                context.view_show.text = ""
                for (i in 0..context.vlist.size - 1) {
                    context.view_show.append("${context.vlist[i].note_id} : ${context.vlist[i].title} : ${context.vlist[i].content}\n")
                }
                Toast.makeText(context,"after ${context.vlist.size-1} ", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"No Data Found ", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
