package com.stanislavkorneev.korneevapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.ItemLoanBinding
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.entities.LoanState

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

        fun bind(loanListItem: Loan) {
            with(binding) {
                simpleLoanAmount.text = loanListItem.amount.toString()
                simpleLoanDays.text = loanListItem.period.toString()
                loanPercent.text = loanListItem.percent.toString()
                loanDateText.text = loanListItem.date
                loanStatusText.text = when (loanListItem.state) {
                    LoanState.APPROVED -> LoanState.APPROVED.description
                    LoanState.REJECTED -> LoanState.REJECTED.description
                    LoanState.REGISTERED -> LoanState.REGISTERED.description
                }
            }

            binding.loanCard.setOnClickListener {
                loanOnClick(loanListItem.id)
            }
        }
    }
}

