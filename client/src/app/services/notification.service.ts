import { Injectable } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private snackBar: MatSnackBar) { }

  showSnackBar(message: string): void {
    // this.snackBar.open(message, null, {duration: 1000});
    this.snackBar.open(message, undefined, {
      duration: 2000 /* сообщение будет показано в течении 2 сек */
    });
  }
}
