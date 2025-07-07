package app.onii.capacitor.plugin.elgin;

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
}
