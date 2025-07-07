package app.onii.capacitor.plugin.elgin;

import android.app.Activity;
import android.util.Log;
import com.elgin.elginexperience.Services.PrinterService;
import java.util.HashMap;
import java.util.Map;

public class Elgin {

    private PrinterService printerService;

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public void init(Activity activity) {
        Log.i("Elgin", "Initializing Elgin with activity");
        printerService = new PrinterService(activity);
    }

    public int initPrinter() {
        if (printerService == null) {
            Log.e("Elgin", "PrinterService not initialized. Call init() first.");
            return -1;
        }
        
        Log.i("Elgin", "Initializing internal printer");
        return printerService.printerInternalImpStart();
    }

    public int printText(String text, String align, String font, int fontSize, boolean isUnderline, boolean isBold) {
        if (printerService == null) {
            Log.e("Elgin", "PrinterService not initialized. Call init() first.");
            return -1;
        }

        Log.i("Elgin", "Printing text: " + text);
        
        Map<String, Object> textParams = new HashMap<>();
        textParams.put("text", text);
        textParams.put("align", align);
        textParams.put("font", font);
        textParams.put("fontSize", fontSize);
        textParams.put("isUnderline", isUnderline);
        textParams.put("isBold", isBold);
        
        return printerService.imprimeTexto(textParams);
    }
}
