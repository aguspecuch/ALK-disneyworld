package ar.com.alkemy.disneyworld.models.response;

public class GenericResponse {

    public GenericResponse() {

    }

    public GenericResponse(boolean isOk, String message) {
        this.isOk = isOk;
        this.message = message;
    }

    public GenericResponse(boolean isOk, Integer id, String message) {
        this.isOk = isOk;
        this.id = id;
        this.message = message;
    }

    public boolean isOk;
    public Integer id;
    public String message;
}
