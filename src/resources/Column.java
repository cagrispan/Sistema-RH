package resources;

public class Column
{
    public String columnName;
    public Class columnClass;         

    Column(String headerName, Class columnClass) {
        this.columnName = headerName;
        this.columnClass = columnClass;
    }
}