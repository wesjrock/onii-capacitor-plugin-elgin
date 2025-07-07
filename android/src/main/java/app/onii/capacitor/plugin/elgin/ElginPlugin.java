package app.onii.capacitor.plugin.elgin;

import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Elgin")
public class ElginPlugin extends Plugin {

    private Elgin implementation = new Elgin();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void initPlugin(PluginCall call) {
        getActivity()
            .runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        implementation.init(getActivity());
                    }
                }
            );

        JSObject ret = new JSObject();
        call.resolve(ret);
    }

    @PluginMethod
    public void initPrinter(PluginCall call) {
        int result = implementation.initPrinter();
        
        JSObject ret = new JSObject();
        ret.put("success", result == 0);
        ret.put("resultCode", result);
        call.resolve(ret);
    }

    @PluginMethod
    public void printText(PluginCall call) {
        String text = call.getString("text");
        String align = call.getString("align", "Esquerda");
        String font = call.getString("font", "FONT A");
        int fontSize = call.getInt("fontSize", 0);
        boolean isUnderline = call.getBoolean("isUnderline", false);
        boolean isBold = call.getBoolean("isBold", false);

        if (text == null) {
            call.reject("Text parameter is required");
            return;
        }

        int result = implementation.printText(text, align, font, fontSize, isUnderline, isBold);
        
        JSObject ret = new JSObject();
        ret.put("success", result == 0);
        ret.put("resultCode", result);
        call.resolve(ret);
    }

    @PluginMethod
    public void printQrcode(PluginCall call) {
        String text = call.getString("text");
        String align = call.getString("align", "Esquerda");
        int qrSize = call.getInt("qrSize", 4);

        if (text == null) {
            call.reject("Text parameter is required");
            return;
        }

        int result = implementation.printQrcode(text, align, qrSize);
        
        JSObject ret = new JSObject();
        ret.put("success", result == 0);
        ret.put("resultCode", result);
        call.resolve(ret);
    }

    @PluginMethod
    public void cutPaper(PluginCall call) {
        int lines = call.getInt("lines", 1);

        // Debug logging
        Log.d("ElginPlugin", "Paper cutting initiated with " + lines + " lines advance");

        int result = implementation.cutPaper(lines);
        
        // Debug logging for result
        Log.d("ElginPlugin", "Paper cut completed with result code: " + result);
        
        JSObject ret = new JSObject();
        ret.put("success", result == 0);
        ret.put("resultCode", result);
        call.resolve(ret);
    }

    @PluginMethod
    public void jumpLines(PluginCall call) {
        int lines = call.getInt("lines", 1);

        // Debug logging
        Log.d("ElginPlugin", "Jumping " + lines + " lines");

        int result = implementation.jumpLines(lines);
        
        // Debug logging for result
        Log.d("ElginPlugin", "Jump lines completed with result code: " + result);
        
        JSObject ret = new JSObject();
        ret.put("success", result == 0);
        ret.put("resultCode", result);
        call.resolve(ret);
    }
}
