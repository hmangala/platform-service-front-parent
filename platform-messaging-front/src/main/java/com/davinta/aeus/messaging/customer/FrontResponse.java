/**
 * Copyright (C) Davinta Technologies 2017. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Davinta Technologies. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Davinta Technologies.
 */

package com.davinta.aeus.messaging.customer;

import com.davinta.aeus.messaging.base.BaseResponseMessageModel;

/**
 * The Class FrontResponse.
 * @author Harish Mangala
 */
public class FrontResponse extends BaseResponseMessageModel<FrontRequest> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4771820046531346670L;

	/** The message. */
	private FrontRequest message;

	/**
	 * Gets the message.
	 * @return the message
	 */
	@Override
	public FrontRequest getMessage() {
		return message;
	}

	/**
	 * Set message.
	 * @param message message
	 */
	@Override
	public void setMessage(FrontRequest message) {
		this.message = message;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerResponse [");
		if (message != null) {
			builder.append("message=");
			builder.append(message);
		}
		builder.append("]");
		return builder.toString();
	}

}
