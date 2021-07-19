package de.malik.mylibrary.general;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

public class UtilsLib {

    /**
     * a int used to pass into methods to tell that there is no animation wanted
     */
    public static final int NO_ANIM = -1;

    /**
     * a field used to handle processes
     */
    private AppCompatActivity activity;

    /**
     * uses passed <code>activity</code> to convert it into a field. It is used to handle processes
     * @param activity a class that is extending <code>AppCompatActivity</code>. Normally it is the
     *                 MainActivity
     */
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

    /**
     * replaces the View <code>containerViewId</code> with <code>newFragment</code>
     * @param containerViewId the id of the view which will be replaced
     * @param newFragment the fragment which will be set to the place of the View
     * @param anim the animation with which the new fragment will be placed
     */
    public void replaceView(int containerViewId, Fragment newFragment, @AnimRes @AnimatorRes int anim) {
        if (anim == NO_ANIM)
            activity.getSupportFragmentManager().beginTransaction().replace(containerViewId, newFragment).commit();
        else
            activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(anim, 0).replace(containerViewId, newFragment).commit();
    }

    public long createHighestId(ArrayList<Long> ids) {
        if (ids.size() == 0) return 1;
        return Collections.max(ids) +1;
    }
}
