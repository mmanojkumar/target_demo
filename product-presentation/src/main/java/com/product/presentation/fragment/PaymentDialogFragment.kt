package com.product.presentation.fragment

import android.os.Bundle
import android.text.Editable
import com.product.presentation.validator.validateCreditCard
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.product.presentation.R
import javax.xml.validation.Validator


class PaymentDialogFragment : DialogFragment() {

  private lateinit var submitButton: Button
  private lateinit var creditCardInput: EditText

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.dialog_payment, container, false)

    submitButton = root.findViewById(R.id.submit)
    creditCardInput = root.findViewById(R.id.card_number)
    submitButton.isEnabled = false
    val cancelButton: Button = root.findViewById(R.id.cancel)
    root.findViewById<EditText>(R.id.card_number).addTextChangedListener(object:TextWatcher{
      override fun afterTextChanged(s: Editable?) {
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        submitButton.isEnabled = validateCreditCard(s.toString())
      }
    })

    cancelButton.setOnClickListener { dismiss() }
    submitButton.setOnClickListener { dismiss() }

    // TODO enable the submit button based on card number validity using Validators.validateCreditCard()

    return root
  }

}