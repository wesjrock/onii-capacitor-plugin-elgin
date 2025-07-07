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
}
