export interface ElginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;

  initPlugin(): Promise<{}>;

  initPrinter(): Promise<{ success: boolean; resultCode: number }>;

  printText(options: {
    text: string;
    align?: string; // "Esquerda", "Centralizado", "Direita"
    font?: string; // "FONT A" or "FONT B"
    fontSize?: number;
    isUnderline?: boolean;
    isBold?: boolean;
  }): Promise<{ success: boolean; resultCode: number }>;

  printQrcode(options: {
    text: string;
    align?: string; // "Esquerda", "Centralizado", "Direita"
    qrSize?: number; // QR code size
  }): Promise<{ success: boolean; resultCode: number }>;

  cutPaper(options: {
    lines?: number; // Number of lines to advance before cutting (default: 1)
  }): Promise<{ success: boolean; resultCode: number }>;
}
