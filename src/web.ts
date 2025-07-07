import { WebPlugin } from '@capacitor/core';

import type { ElginPlugin } from './definitions';

export class ElginWeb extends WebPlugin implements ElginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async initPlugin(): Promise<{}> {
    console.log('Initializing Plugin!');
    return {};
  }

  async initPrinter(): Promise<{ success: boolean; resultCode: number }> {
    console.log('Initializing Printer!');
    return { success: true, resultCode: 0 };
  }

  async printText(options: {
    text: string;
    align?: string;
    font?: string;
    fontSize?: number;
    isUnderline?: boolean;
    isBold?: boolean;
  }): Promise<{ success: boolean; resultCode: number }> {
    console.log('Imprimindo: ', options);
    return { success: true, resultCode: 0 };
  }

  async printQrcode(options: {
    text: string;
    align?: string;
    qrSize?: number;
  }): Promise<{ success: boolean; resultCode: number }> {
    console.log('Printing QR Code: ', options);
    return { success: true, resultCode: 0 };
  }
}
