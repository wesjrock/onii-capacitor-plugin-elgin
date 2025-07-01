import { registerPlugin } from '@capacitor/core';

import type { ElginPlugin } from './definitions';

const Elgin = registerPlugin<ElginPlugin>('Elgin', {
  web: () => import('./web').then((m) => new m.ElginWeb()),
});

export * from './definitions';
export { Elgin };
