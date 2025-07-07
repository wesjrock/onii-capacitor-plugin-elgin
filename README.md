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
printText(options: { text: string; align: number; font: string; fontSize: number; }) => Promise<boolean>
```

| Param         | Type                                                                          |
| ------------- | ----------------------------------------------------------------------------- |
| **`options`** | <code>{ text: string; align: number; font: string; fontSize: number; }</code> |

**Returns:** <code>Promise&lt;boolean&gt;</code>

--------------------

</docgen-api>
