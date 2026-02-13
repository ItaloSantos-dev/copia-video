import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerMetrics } from './server-metrics';

describe('ServerMetrics', () => {
  let component: ServerMetrics;
  let fixture: ComponentFixture<ServerMetrics>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServerMetrics]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServerMetrics);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
