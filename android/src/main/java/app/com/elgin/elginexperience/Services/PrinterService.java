package com.elgin.elginexperience.Services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;

import com.elgin.e1.Impressora.Termica;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class PrinterService {
    private static final String TAG = "PrinterService";
    public Activity mActivity;

    //Variavel utilizada para a verificação de se a conexão atual é por impressora interna ou não
    public boolean isPrinterInternSelected;

    public PrinterService(Activity activity) {
        this.mActivity = activity;
        logDebug("Initializing PrinterService with activity: " + activity);
        Termica.setContext(mActivity);
        logDebug("Termica context set");
    }

    // Construtor sem argumento utilizado apenas para a criação de testes de validaçõ dos parâmetros.
    public PrinterService() {
        logDebug("Initializing PrinterService with no activity");
    }

    public int printerInternalImpStart() {
        logDebug("Starting internal printer");
        printerStop();
        int result = Termica.AbreConexaoImpressora(6, "M8", "", 0);
        logDebug("Internal printer start result: " + result);

        if (result == 0) {
            this.isPrinterInternSelected = true;
            logDebug("Internal printer selected successfully");
        } else {
            logError("Failed to start internal printer, error code: " + result);
        }

        return result;
    }

    public void printerStop() {
        logDebug("Stopping printer");
        Termica.FechaConexaoImpressora();
        logDebug("Printer stopped");
    }

    public int AvancaLinhas(Map<String, Object> map) {
        final int lines = (Integer) Objects.requireNonNull(map.get("quant"), "quant");
        logDebug("Advancing " + lines + " lines");

        int result = Termica.AvancaPapel(lines);
        logDebug("Advance lines result: " + result);
        return result;
    }

    public int cutPaper(Map<String, Object> map) {
        final int lines = (Integer) Objects.requireNonNull(map.get("quant"), "quant");
        logDebug("Cutting paper with " + lines + " lines advance");

        int result = Termica.Corte(lines);
        logDebug("Cut paper result: " + result);
        return result;
    }

    public int imprimeTexto(Map<String, Object> map) {
        final String text = (String) Objects.requireNonNull(map.get("text"), "text");
        final String align = (String) Objects.requireNonNull(map.get("align"), "align");
        final String font = (String) Objects.requireNonNull(map.get("font"), "font");
        final int fontSize = (Integer) Objects.requireNonNull(map.get("fontSize"), "fontSize");

        logDebug("Printing text: '" + text + "', align: " + align + ", font: " + font + ", fontSize: " + fontSize);

        int result = 0;
        int alignValue = 0;
        int styleValue = 0;

        // ALINHAMENTO VALUE
        switch (align) {
            case "Esquerda":
                alignValue = 0;
                break;
            case "Centralizado":
                alignValue = 1;
                break;
            default:
                alignValue = 2;
                break;
        }

        //STILO VALUE
        if (font.equals("FONT B")) {
            styleValue += 1;
        }
        if ((boolean) Objects.requireNonNull(map.get("isUnderline"))) {
            styleValue += 2;
        }
        if ((boolean) Objects.requireNonNull(map.get("isBold"))) {
            styleValue += 8;
        }

        logDebug("Text print parameters - alignValue: " + alignValue + ", styleValue: " + styleValue);
        result = Termica.ImpressaoTexto(text, alignValue, styleValue, fontSize);
        logDebug("Print text result: " + result);

        return result;
    }

    private int codeOfBarCode(String barCodeName) {
        switch (barCodeName) {
            case "UPC-A":
                return 0;
            case "UPC-E":
                return 1;
            case "EAN 13":
            case "JAN 13":
                return 2;
            case "EAN 8":
            case "JAN 8":
                return 3;
            case "CODE 39":
                return 4;
            case "ITF":
                return 5;
            case "CODE BAR":
                return 6;
            case "CODE 93":
                return 7;
            case "CODE 128":
                return 8;
            default:
                throw new AssertionError(barCodeName);
        }
    }

    public int imprimeBarCode(Map<String, Object> map) {
        final int barCodeType = codeOfBarCode((String) Objects.requireNonNull(map.get("barCodeType"), "barCodeType"));
        final String text = (String) Objects.requireNonNull(map.get("text"), "text");
        final int height = (Integer) Objects.requireNonNull(map.get("height"), "height");
        final int width = (Integer) Objects.requireNonNull(map.get("width"), "width");
        final String align = (String) Objects.requireNonNull(map.get("align"), "align");

        final int hri = 4; // NO PRINT
        final int result;
        final int alignValue;

        // ALINHAMENTO VALUE
        switch (align) {
            case "Esquerda":
                alignValue = 0;
                break;
            case "Centralizado":
                alignValue = 1;
                break;
            default:
                alignValue = 2;
                break;
        }

        Termica.DefinePosicao(alignValue);

        result = Termica.ImpressaoCodigoBarras(barCodeType, text, height, width, hri);

        return result;
    }

    public int imprimeQR_CODE(Map<String, Object> map) {
        final int size = (Integer) Objects.requireNonNull(map.get("qrSize"), "qrSize");
        final String text = (String) Objects.requireNonNull(map.get("text"), "text");
        final String align = (String) Objects.requireNonNull(map.get("align"), "align");

        final int nivelCorrecao = 2;
        final int result;
        final int alignValue;

        // ALINHAMENTO VALUE
        switch (align) {
            case "Esquerda":
                alignValue = 0;
                break;
            case "Centralizado":
                alignValue = 1;
                break;
            default:
                alignValue = 2;
                break;
        }

        Termica.DefinePosicao(alignValue);

        result = Termica.ImpressaoQRCode(text, size, nivelCorrecao);
        return result;
    }

    public int imprimeImagem(Bitmap bitmap) {
        //Verifica se o método de impressão atual é por impressora interna ou externa e utiliza a função adequada para cada um
        return isPrinterInternSelected ? Termica.ImprimeBitmap(bitmap) : Termica.ImprimeImagem(bitmap);
    }

    public int statusGaveta() {
        logDebug("Checking drawer status");
        int status = Termica.StatusImpressora(1);
        logDebug("Drawer status: " + status);
        return status;
    }

    public int abrirGaveta() {
        logDebug("Opening drawer");
        int result = Termica.AbreGavetaElgin();
        logDebug("Open drawer result: " + result);
        return result;
    }

    public int statusSensorPapel() {
        logDebug("Checking paper sensor status");
        int status = Termica.StatusImpressora(3);
        logDebug("Paper sensor status: " + status);
        return status;
    }

    // Debug utility methods
    private void logDebug(String message) {
        Log.d(TAG, message);
    }

    private void logError(String message) {
        Log.e(TAG, message);
    }

    // Method to get the current printer state for debugging
    public String getPrinterStatus() {
        int statusCode = Termica.StatusImpressora(0);
        String statusMessage;

        switch (statusCode) {
            case 0:
                statusMessage = "Status Normal";
                break;
            case 1:
                statusMessage = "Gaveta Aberta";
                break;
            case 2:
                statusMessage = "Gaveta Fechada";
                break;
            case 3:
                statusMessage = "Erro no Cortador";
                break;
            case 4:
                statusMessage = "Pouco Papel";
                break;
            case 5:
                statusMessage = "Papel OK";
                break;
            case 6:
                statusMessage = "Pouco Papel";
                break;
            case 7:
                statusMessage = "Sem Papel";
                break;
            case 8:
                statusMessage = "Superaquecimento";
                break;
            case 9:
                statusMessage = "Tampa Aberta";
                break;
            case 10:
                statusMessage = "Erro de Escrita/Leitura";
                break;
            default:
                statusMessage = "Status Desconhecido (" + statusCode + ")";
                break;
        }

        logDebug("Current printer status: " + statusMessage);
        return statusMessage;
    }
}
