package com.example.OrderMS.Service;

import com.infobip.ApiClient;
import com.infobip.ApiException;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsAdvancedTextualRequest;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsResponse;
import com.infobip.model.SmsTextualMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfobipSmsService {

    private final String apiKey;
    private final String baseURL;

    private final SmsApi smsApi;

    public InfobipSmsService(
            @Value("${infobip.apiKey}") String apiKey,
            @Value("${infobip.baseURL}") String baseURL
    ) {
        this.apiKey = apiKey;
        this.baseURL = baseURL;

        ApiClient apiClient = ApiClient.forApiKey(ApiKey.from(apiKey))
                .withBaseUrl(BaseUrl.from(baseURL))
                .build();

        this.smsApi = new SmsApi(apiClient);
    }

    public void sendSms(String message, String recipient) {
        SmsTextualMessage smsMessage = new SmsTextualMessage()
                .from("InfoSMS")
                .addDestinationsItem(new SmsDestination().to(recipient))
                .text(message);

        SmsAdvancedTextualRequest smsMessageRequest = new SmsAdvancedTextualRequest()
                .messages(List.of(smsMessage));

        try {
            SmsResponse smsResponse = smsApi.sendSmsMessage(smsMessageRequest).execute();
            // Handle successful response
        } catch (ApiException apiException) {
            // Handle exception
        }
    }
}
