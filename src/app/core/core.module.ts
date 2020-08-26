import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppService } from './app.service';
import { XhrInterceptor } from './http-interceptors/xhr-interceptor'

@NgModule({
  imports: [ HttpClientModule ],
  providers: [
    AppService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: XhrInterceptor,
      multi: true
    }
  ]
})
export class CoreModule { }
