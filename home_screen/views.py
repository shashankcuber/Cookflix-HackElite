from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.decorators import api_view,permission_classes,authentication_classes
import requests
from rest_framework import status
from data import *

@api_view(('GET',))
def get_food_list(request):
    print("Chal gya")
    return Response({"Home PAGE"},status=status.HTTP_200_OK)
    


