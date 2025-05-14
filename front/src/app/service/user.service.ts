import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserPublicInfo} from '../model/user-public-info';
import {UserCredential} from '../model/user-credential';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly serverUrl: string;

  constructor(private http: HttpClient) {
    this.serverUrl = 'http://localhost:8080/';
  }

  public findAll(): Observable<UserPublicInfo[]> {
    return this.http.get<UserPublicInfo[]>(this.serverUrl + 'users');
  }

  public save(user: UserCredential) {
    return this.http.post<UserPublicInfo>(this.serverUrl + 'signIn', user);
  }
}
