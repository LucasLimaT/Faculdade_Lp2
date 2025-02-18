package ifmt.cba.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtil {
    
    private LocalDate dataInterna;
    private static final DateTimeFormatter formatoPersonalizado;
    private static final String[] mesesDoAno;

    static {
        formatoPersonalizado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        mesesDoAno = new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    }

    public DataUtil() {
        this.dataInterna = LocalDate.now();
    }

    public DataUtil(LocalDate data) {
        this.dataInterna = data;
    }
    
    public LocalDate getDataInterna() {
        return dataInterna;
    }

    public void setDataInterna(LocalDate dataInterna) {
        this.dataInterna = dataInterna;
    }

    @Override
    public String toString() {
        return this.dataInterna.format(formatoPersonalizado);
    }

    public java.sql.Date getDataSQL() {
        return java.sql.Date.valueOf(this.dataInterna);
    }

    public static String[] mesesDoAno() {
        return mesesDoAno;
    }
    
    public static DataUtil stringToDate(String dataString) {

        LocalDate dataTemp;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataTemp = LocalDate.parse(dataString, formatter);
        } catch (DateTimeParseException ex) {
            dataTemp = null;
        }

        return new DataUtil(dataTemp);
    }

    public static String nomeMes(int mes) {
        if (mes >= 1 && mes <= 12) {
            return mesesDoAno[mes-1];
        } else {
            return "Mes Indefinido";
        }
    }

    public static String[] intervaloAnos(LocalDate dataInicial, LocalDate dataFinal) {
        String[] retorno = new String[dataFinal.getYear() - dataInicial.getYear() + 1];
        int x = 0;
        for (int i = dataInicial.getYear(); i <= dataFinal.getYear(); i++) {
            retorno[x++] = String.valueOf(i);
        }
        return retorno;
    }

    public int anoData() {
        return this.dataInterna.getYear();
    }

    public static int anoData(LocalDate data) {
        return data.getYear();
    }

    public int mesData() {
        return this.dataInterna.getMonth().getValue();
    }

    public static int mesData(LocalDate data) {
        return data.getMonth().getValue();
    }

    public int diaData() {
        return this.dataInterna.getDayOfYear();
    }

    public static int diaData(LocalDate data) {
        return data.getDayOfYear();
    }

    public static LocalDate dataMenosAnos(LocalDate data, int anos) {
        return data.minusYears(anos);
    }

    public static LocalDate dataMenosMeses(LocalDate data, int meses) {
        return data.minusMonths(meses);
    }

    public static LocalDate dataMenosDias(LocalDate data, int dias) {
        return data.minusDays(dias);
    }

    public static LocalDate dataMaisAnos(LocalDate data, int anos) {
        return data.plusYears(anos);
    }

    public static LocalDate dataMaisMeses(LocalDate data, int meses) {
        return data.plusMonths(meses);
    }

    public static LocalDate dataMaisDias(LocalDate data, int dias) {
        return data.plusDays(dias);
    }
}
