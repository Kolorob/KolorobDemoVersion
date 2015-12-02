package demo.kolorob.kolorobdemoversion.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.SpannableString;

import demo.kolorob.kolorobdemoversion.helpers.AndroidUnicodedFontSupport;

/**
 * Created by touhid on 10/29/15.
 * @author touhid
 */
public class AppUtils {

    private static Typeface bengaliFontNormalSolmn, bengaliFontBoldSolmn;

    public static SpannableString getUnicodedFormat(AssetManager assetManager, String str) {
        if (bengaliFontNormalSolmn == null)
            bengaliFontNormalSolmn = Typeface.createFromAsset(assetManager,
                    "fonts/solaimanlipinormal.ttf");
        return AndroidUnicodedFontSupport.getCorrectedBengaliFormat(str, bengaliFontNormalSolmn, -1);
    }

    public static SpannableString getUnicodedFormatBold(AssetManager assetManager, String str) {
        if (bengaliFontBoldSolmn == null)
            bengaliFontBoldSolmn = Typeface.createFromAsset(assetManager,
                    "fonts/solaimanlipibold.ttf");
        return AndroidUnicodedFontSupport.getCorrectedBengaliFormat(str, bengaliFontBoldSolmn, -1);
    }
}
