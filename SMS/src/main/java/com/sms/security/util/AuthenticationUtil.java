package com.sms.security.util;

import com.sms.dto.AccountSignInDTO;

public interface AuthenticationUtil {

	String signIn(AccountSignInDTO dto);
}
