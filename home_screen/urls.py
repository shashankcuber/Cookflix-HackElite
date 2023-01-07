from django.urls import path
from home_screen import views

urlpatterns=[
   path('home/',views.get_food_list,name='login'),
]