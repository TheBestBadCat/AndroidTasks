package com.stanislavkorneev.korneevapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stanislavkorneev.korneevapp.databinding.ItemLoanBinding
import com.stanislavkorneev.korneevapp.domain.entities.Loan

class LoansListAdapter(private val loanOnClick: (Int) -> Unit) :
    RecyclerView.Adapter<LoansListAdapter.LoanViewHolder>() {

    var loans: List<Loan> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val binding =
            ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding, loanOnClick)
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.bind(loans[position])
    }

    override fun getItemCount(): Int = loans.size

    inner class LoanViewHolder(private val binding: ItemLoanBinding, private val loanOnClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Loan) {

            binding.textLoanAmount.text = item.amount.toString()
            binding.textLoanPercent.text = item.percent.toString()
            binding.textLoanStatus.text = item.state.toString()

            binding.loanCard.setOnClickListener {
                loanOnClick(item.id)
            }
        }
    }
}

