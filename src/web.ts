import { WebPlugin } from '@capacitor/core';

import type { ElginPlugin } from './definitions';

export class ElginWeb extends WebPlugin implements ElginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
