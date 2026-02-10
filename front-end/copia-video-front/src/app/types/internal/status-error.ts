import { HttpStatusCode } from "@angular/common/http";

export interface StatusError{
    status: HttpStatusCode,
    menssage:string
}