import { Elgin } from 'onii-capacitor-plugin-elgin';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    Elgin.echo({ value: inputValue })
}
