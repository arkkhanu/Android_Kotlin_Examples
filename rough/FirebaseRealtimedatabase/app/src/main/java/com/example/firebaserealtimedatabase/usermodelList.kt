package com.example.firebaserealtimedatabase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

//class usermodelList(var conte: Context,var list: ArrayList<UserModel>)
//    : ArrayAdapter<UserModel>(conte,R.layout.listviewlist,list)
//{
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        return super.getView(position, convertView, parent)
//
//        val view = LayoutInflater.from(conte).inflate(R.layout.listviewlist,null,true)
//
//        val name = view.findViewById<TextView>(R.id.name_list)
//        val email = view.findViewById<TextView>(R.id.email_list)
//        val age = view.findViewById<TextView>(R.id.age_list)
//        val pass = view.findViewById<TextView>(R.id.pass_list)
//
//        val temp = list[position]
//
//        name.text = temp.name
//        email.text = temp.email
//        age.text = temp.age
//        pass.text = temp.password
//
//        return view
//    }
//}

class usermodelList(var context:Context,var list :ArrayList<UserModel>):RecyclerView.Adapter<usermodelList.UserViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.listviewlist,p0,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: UserViewHolder, p1: Int) {
       p0.binditem(list[p1])
    }

    inner class UserViewHolder(v :View):RecyclerView.ViewHolder(v){

        val name = itemView.findViewById<TextView>(R.id.name_list)
        val email = itemView.findViewById<TextView>(R.id.email_list)
        val age = itemView.findViewById<TextView>(R.id.age_list)
        val pass = itemView.findViewById<TextView>(R.id.pass_list)

        fun binditem(temp:UserModel){
            name.text  = temp.name
            email.text = temp.email
            age.text   = temp.age
            pass.text  = temp.password
        }
    }
}