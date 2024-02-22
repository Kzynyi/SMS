package com.sms.util;

import org.springframework.stereotype.Component;

public interface AccountUtil {

	boolean checkAccountExists(String email, String phone);
}
