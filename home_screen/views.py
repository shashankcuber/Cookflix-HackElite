from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.decorators import api_view,permission_classes,authentication_classes
import requests
from rest_framework import status
from data import *
import pandas as pd
from home_screen import utils
import json 

@api_view(('GET',))
def get_food_list(request):
    try:
        
        df = pd.read_csv('data/new_df.csv')
        df_sample = df.head(15)
        list_obj = utils.get_required_list(df_sample)
        return Response({"Result":list_obj},status=status.HTTP_200_OK)

    except Exception as e:
        return Response({"Error message":e},status=status.HTTP_400_BAD_REQUEST)
    


