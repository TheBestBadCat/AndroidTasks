package com.stanislavkorneev.korneevapp.presentation

import android.app.AlertDialog
import android.content.Context
import com.stanislavkorneev.korneevapp.R

object DialogManager {

    fun showExceptionMessage(context: Context, exceptionSimpleName: String) {
        when (exceptionSimpleName) {
            "HttpException" -> showHttpExceptionDialog(context)
            "UnknownHostException" -> showUnknownHostExceptionDialog(context)
            "IllegalArgumentException" -> showInputExceptionDialog(context)
            else -> showUnknownExceptionDialog(context)
        }
    }

    fun showLoginSuccessDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(R.string.login_success_title)
            .setMessage(R.string.login_success_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showHomeGuideDialog(context)
            }.show()
    }

    fun showGetLoanCashGuide(context: Context,) {
        AlertDialog.Builder(context)
            .setTitle(R.string.get_loan_cash_title)
            .setMessage(R.string.get_loan_cash_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showPlugDialog(context)
            }.show()
    }

    fun showGetLoanCardGuide(context: Context,) {
        AlertDialog.Builder(context)
            .setTitle(R.string.get_loan_card_title)
            .setMessage(R.string.get_loan_card_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showPlugDialog(context)
            }.show()
    }

    fun showLogoutDialog(context: Context, logoutAction: () -> Unit) {
        AlertDialog.Builder(context)
            .setTitle(R.string.profile_logout_title)
            .setMessage(R.string.profile_logout_message)
            .setPositiveButton(R.string.profile_logout_positive_button) { dialog, _ ->
                dialog.dismiss()
                logoutAction()
            }
            .setNegativeButton(R.string.profile_logout_negative_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun showRegistrationSuccessDialog(context: Context) {
        showSimpleDialog(
            context = context,
            titleResource = R.string.registration_success_title,
            messageResource = R.string.registration_success_message
        )
    }

    fun showCreateLoanSuccessDialog(context: Context) {
        showSimpleDialog(
            context = context,
            titleResource = R.string.create_loan_success_title,
            messageResource = R.string.create_loan_success_message
        )
    }

    private fun showPlugDialog(context: Context) {
        showSimpleDialog(
            context = context,
            titleResource = R.string.plug_dialog_title,
            messageResource = R.string.plug_dialog_message
        )
    }

    private fun showHomeGuideDialog(context: Context) {
        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_baseline_home_24)
            .setTitle(R.string.home_guide_title)
            .setMessage(R.string.home_guide_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showLoanGuideDialog(context)
            }.show()
    }

    private fun showLoanGuideDialog(context: Context) {
        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_baseline_credit_card_24)
            .setTitle(R.string.loan_guide_title)
            .setMessage(R.string.loan_guide_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showProfileGuideDialog(context)
            }.show()
    }

    private fun showProfileGuideDialog(context: Context) {
        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_baseline_person_24)
            .setTitle(R.string.profile_guide_title)
            .setMessage(R.string.profile_guide_message)
            .setPositiveButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun showInputExceptionDialog(context: Context) {
        showSimpleDialog(
            context = context,
            titleResource = R.string.input_exception_title,
            messageResource = R.string.input_exception_message
        )
    }

    private fun showUnknownHostExceptionDialog(context: Context) {
        showSimpleDialog(
            context = context,
            titleResource = R.string.host_exception_title,
            messageResource = R.string.host_exception_message
        )
    }

    private fun showHttpExceptionDialog(context: Context) {
        showSimpleDialog(
            context = context,
            titleResource = R.string.http_exception_title,
            messageResource = R.string.http_exception_message
        )
    }

    private fun showUnknownExceptionDialog(context: Context) {
        showSimpleDialog(
            context = context,
            titleResource = R.string.unknown_exception_title,
            messageResource = R.string.unknown_exception_message
        )
    }

    private fun showSimpleDialog(context: Context, titleResource: Int, messageResource: Int) {
        AlertDialog.Builder(context)
            .setTitle(titleResource)
            .setMessage(messageResource)
            .setPositiveButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}