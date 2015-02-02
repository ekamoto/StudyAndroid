package com.example.hisamoto.commandline.enums;

/**
 * Created by robsono on 20/10/14.
 */
public enum EnumCommandLine {
    REINICIAR("reboot"),
    DESLIGAR("reboot -p"),
    BARRAINFERIORESCONDER("service call activity 42 s16 com.android.systemui"),
    BARRAINFERIORMOSTRAR("am startservice -n com.android.systemui/.SystemUIService"),
    ALTERARDATASISTEMA("date -s "),
    ALTERARBRILHO("echo \"comando\" > ./sys/devices/platform/rk29_backlight/backlight/rk28_bl/brightness"),
    /* retorna o número 2 caso o serviço estiver ativo */
    WATCHDOG_PM_CHECK("busybox ps | busybox grep /system/bin/startservicepm.sh | busybox wc -l"),
    WATCHDOG_PM_START("busybox nohup /system/bin/startservicepm.sh > /dev/null 2>&1 &"),
    INSTALARAPK("pm install -r comando"),
    COMANDO("");

    private final String comando;

    private EnumCommandLine(final String comando){
        this.comando = comando;
    }

    public String getComando(){
        return comando;
    }

    public String getComando(String parametro){
        return comando + parametro;
    }

    public String getComandoParametro(String parametro) {
        return this.comando.replaceAll("comando", parametro);
    }
}
