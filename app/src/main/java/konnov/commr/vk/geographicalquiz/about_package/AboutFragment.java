package konnov.commr.vk.geographicalquiz.about_package;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import konnov.commr.vk.geographicalquiz.BuildConfig;
import konnov.commr.vk.geographicalquiz.R;

public class AboutFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about_fragment);

    Preference vkContact = findPreference("about_vk");
    vkContact.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference) {
            Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mr.konnov"));
            startActivity(browseIntent);
            return true;
        }
    });


    Preference feedback = findPreference("about_feedback");
    feedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference) {

            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(new Uri.Builder().scheme("mailto").build());
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"2normalhuman@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "[GeoQiuz] Feedback");
            email.putExtra(Intent.EXTRA_TEXT, "\nMy device info: \n" + DeviceInfo.getDeviceInfo()
                    + "\nApp version: " + BuildConfig.VERSION_NAME
                    + "\nFeedback:" + "\n");
            try {
                startActivity(Intent.createChooser(email, "Send feedback"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText((getActivity()), R.string.about_no_email, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    });

    }
}
