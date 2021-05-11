package de.malik.mylibrary.general;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class UtilsLib {

    public static int NO_ANIM = -1;

    private AppCompatActivity activity;

    public UtilsLib(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * checks if the argument String <code>s</code> contains the one of the String in
     * <code>values</code>
     * @param s the source. This String will be checked if it contains some of the Strings
     * @param values the list of Strings which will be checked if the source contains one of them
     * @return true if the source <code>s</code> contains one of the Strings in <code>values</code>,
     *         otherwise false will be returned
     */
    public static boolean containsString(String s, String... values) {
        for (String value : values) {
            if (s.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public void replaceFragment(int containerViewId, Fragment newFragment, @AnimRes @AnimatorRes int anim) {
        if (anim == NO_ANIM)
            activity.getSupportFragmentManager().beginTransaction().replace(containerViewId, newFragment).commit();
        else
            activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(anim, 0).replace(containerViewId, newFragment).commit();
    }
}
