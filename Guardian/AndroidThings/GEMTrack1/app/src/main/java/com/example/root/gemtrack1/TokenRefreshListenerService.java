package com.example.root.gemtrack1;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;
import com.example.root.gemtrack1.RegistrationService;

/**
 * Created by root on 8/6/17.
 */
//

//InstanceIDListenerService that can handle the periodic refreshes of Registration tokens
public class TokenRefreshListenerService extends InstanceIDListenerService{
    @Override
    public void onTokenRefresh() {
        Intent i = new Intent(this, RegistrationService.class);
        startService(i);
    }
}