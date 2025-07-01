export interface ElginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
