import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMetrics } from './user-metrics';

describe('UserMetrics', () => {
  let component: UserMetrics;
  let fixture: ComponentFixture<UserMetrics>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserMetrics]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserMetrics);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
