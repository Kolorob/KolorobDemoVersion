package demo.kolorob.kolorobdemoversion.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import androidbangladesh.bengali.support.BengaliUnicodeString;

public class UnicodeFontUtils {

    public static String SUTONNI_MJ = "sutonni.TTF";
    public static String SOLAIMAN_LIPI = "solaimanlipinormal.ttf";

    public static String WEBVIEW_LOADED = "loaded";

    public static void log(Object obj, String msg) {
        Log.d(obj.getClass().getSimpleName(), msg);
    }

    public static SpannableString getSpannableWithFont(Context context, String text, Typeface font, float size) {
        SpannableString retVal = new SpannableString(text);
        TypefaceSpan span = new TypefaceSpan(font);
        retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (size != -1) {
            retVal.setSpan(new RelativeSizeSpan(size), 0, retVal.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return retVal;
    }


    public static SpannableString getBanglaSpannableWithSize(String text, Typeface font, float size, boolean banglaSupported) {
        SpannableString retVal = null;
        if (banglaSupported)//device supports bangla
        {
            log(UnicodeFontUtils.class, "Bangla Supported Device");
            retVal = new SpannableString(text);
        } else {
            //device doesn't support,need unicode.
            retVal = new SpannableString(BengaliUnicodeString.getBengaliUTF(text));
        }
        TypefaceSpan span = new TypefaceSpan(font);

        retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (size != -1) {
            retVal.setSpan(new RelativeSizeSpan(size), 0, retVal.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return retVal;
    }

    public static SpannableString getBanglaSpannableWithSize(SpannableString text, Typeface font, float size, boolean banglaSupported) {
        SpannableString retVal = null;
        if (banglaSupported)//device supports bangla
        {
            log(UnicodeFontUtils.class, "Bangla Supported Device");
            retVal = new SpannableString(text);
        } else {
            //device doesn't support,need unicode.
            retVal = new SpannableString(BengaliUnicodeString.getBengaliUTF(text.toString()));
        }
        TypefaceSpan span = new TypefaceSpan(font);

        retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (size != -1) {
            retVal.setSpan(new RelativeSizeSpan(size), 0, retVal.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return retVal;
    }


}
