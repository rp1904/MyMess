package com.byb.bhojan.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.byb.bhojan.model.MessPaymentVoucher;

public class PaymentVoucherValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MessPaymentVoucher.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		MessPaymentVoucher messPaymentVoucher = (MessPaymentVoucher) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.paymentVoucher.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "NotEmpty.paymentVoucher.amount");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "days", "NotEmpty.paymentVoucher.days");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discount", "NotEmpty.paymentVoucher.discount");

		if (messPaymentVoucher.getAmount() < 0) {
			errors.rejectValue("amount", "Min.paymentVoucher.amount");
		}

		if (messPaymentVoucher.getDays() < 1) {
			errors.rejectValue("days", "Min.paymentVoucher.days");
		}

		if (messPaymentVoucher.getDays() > 365) {
			errors.rejectValue("days", "Max.paymentVoucher.days");
		}

		if (messPaymentVoucher.getDiscount() < 0) {
			errors.rejectValue("discount", "Min.paymentVoucher.discount");
		}

		if (messPaymentVoucher.getDiscount() > 100) {
			errors.rejectValue("discount", "Max.paymentVoucher.discount");
		}
	}
}
