package com.stanislavkorneev.korneevapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

        fun bind(item: Loan) {
            val simpleLoanInfo = "Сумма: ${item.amount}₽ срок: ${item.period} дней"
            val percentInfo = "Прцентная ставка: ${item.percent}%"
            val dateLoanInfo = "Дата: ${item.date}"
            binding.simpleLoanText.text = simpleLoanInfo
            binding.loanPercentText.text = percentInfo
            binding.loanDateText.text = dateLoanInfo
            binding.loanStatusText.text = when (item.state) {
                LoanState.APPROVED -> "Статус: Одобрен"
                LoanState.REJECTED -> "Статус: Отклонен"
                LoanState.REGISTERED -> "Статус: Зарегистрирован"
            }

            binding.loanCard.setOnClickListener {
                loanOnClick(item.id)
            }
        }
    }
}

