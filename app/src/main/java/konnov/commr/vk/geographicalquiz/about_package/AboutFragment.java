package konnov.commr.vk.geographicalquiz.about_package;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

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
    }
}
