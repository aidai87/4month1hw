package com.example.a4month1hw.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4month1hw.databinding.ItemBoardBinding
import com.example.a4month1hw.glide.GlideYu


class BoardAdapter(private val data: ArrayList<Board>,  private var finishBoard: FinishBoard) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {



    fun setFinishBoard(finishBoard: FinishBoard) {
        this.finishBoard = finishBoard
    }

   inner class BoardViewHolder(private  var binding : ItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {

       fun bind(position: Int) {
binding.btnStart.setOnClickListener(){
    finishBoard.clickm()
}
           val board: Board = data[position]
           binding.textTitle.text = board.title
           binding.textInfo.text = board.titleDesc
           board.image?.let { binding.ivBoard.GlideYu(it) }
           if(position == 2){
               binding.btnStart.visibility = View.VISIBLE
           }else{
               binding.btnStart.visibility = View.GONE
           }
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
holder.bind(position)
    }

    override fun getItemCount(): Int = 3

    interface FinishBoard{
        fun clickm()
    }

}