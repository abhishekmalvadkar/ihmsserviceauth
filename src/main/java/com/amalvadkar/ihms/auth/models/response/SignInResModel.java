package com.amalvadkar.ihms.auth.models.response;

import com.amalvadkar.ihms.common.models.dto.KeyValueResponseModel;
import com.amalvadkar.ihms.common.models.response.KeyValueResModel;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
public class SignInResModel {

    private boolean isFirstLogin;

    private Instant lastLoginTime;

    private Map<String,Object> metaData;
}
