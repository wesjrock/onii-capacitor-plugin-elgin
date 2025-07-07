export interface ElginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;

  initPlugin(): Promise<{}>;

  initPrinter(): Promise<{ success: boolean; resultCode: number }>;

  printText(options: {
    text: string;
    align: number;
    font: string;
    fontSize: number;
  }): Promise<boolean>;
}
