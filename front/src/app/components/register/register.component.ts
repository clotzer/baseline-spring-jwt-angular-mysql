import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RouterLink } from '@angular/router';
import { IntegrationService } from '../../services/integration.service';
import { LocalStorageService } from '../../services/local-storage.service';
import { SignupRequest } from '../../models/signup-request';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  constructor(
    private integrationService: IntegrationService,
    private storage: LocalStorageService
  ) {}

  request: SignupRequest = new SignupRequest();
  msg: string | undefined;

  signupForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  public onSubmit() {
    this.storage.remove('auth-key');

    const formValue = this.signupForm.value;

    this.request.name = formValue.name;
    this.request.username = formValue.username;
    this.request.password = formValue.password;

    if (this.signupForm.valid) {
      console.log('Form is valid');

      this.integrationService.register(this.request).subscribe({
        next: (res) => {
          console.log(res.response);

          this.msg = res.response;
        },
        error: (err) => {
          console.log('Error Received:', err);
        },
      });
    } else {
      console.log('On submit failed.');
    }
  }
}
