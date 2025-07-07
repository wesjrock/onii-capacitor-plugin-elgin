# onii-capacitor-plugin-elgin

printing functionality for elgin totem

## Install

```bash
npm install onii-capacitor-plugin-elgin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`initPlugin()`](#initplugin)
* [`initPrinter()`](#initprinter)
* [`printText(...)`](#printtext)
* [`printQrcode(...)`](#printqrcode)
* [`cutPaper(...)`](#cutpaper)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### initPlugin()

```typescript
initPlugin() => Promise<{}>
```

**Returns:** <code>Promise&lt;{}&gt;</code>

--------------------


### initPrinter()

```typescript
initPrinter() => Promise<{ success: boolean; resultCode: number; }>
```

**Returns:** <code>Promise&lt;{ success: boolean; resultCode: number; }&gt;</code>

--------------------


### printText(...)

```typescript
printText(options: { text: string; align?: string; font?: string; fontSize?: number; isUnderline?: boolean; isBold?: boolean; }) => Promise<{ success: boolean; resultCode: number; }>
```

| Param         | Type                                                                                                                      |
| ------------- | ------------------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ text: string; align?: string; font?: string; fontSize?: number; isUnderline?: boolean; isBold?: boolean; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; resultCode: number; }&gt;</code>

--------------------


### printQrcode(...)

```typescript
printQrcode(options: { text: string; align?: string; qrSize?: number; }) => Promise<{ success: boolean; resultCode: number; }>
```

| Param         | Type                                                            |
| ------------- | --------------------------------------------------------------- |
| **`options`** | <code>{ text: string; align?: string; qrSize?: number; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; resultCode: number; }&gt;</code>

--------------------


### cutPaper(...)

```typescript
cutPaper(options: { lines?: number; }) => Promise<{ success: boolean; resultCode: number; }>
```

| Param         | Type                             |
| ------------- | -------------------------------- |
| **`options`** | <code>{ lines?: number; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; resultCode: number; }&gt;</code>

--------------------

</docgen-api>
