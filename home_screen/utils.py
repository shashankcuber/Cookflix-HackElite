from . import views
import ast

def make_dictionary(title, ingredients, image):
    res = ast.literal_eval(ingredients)
    obj = {
            "Title": title,
            "ingredient_list": res,
            "image": image,
        }
    return obj


def get_required_list(df_sample):
    print("enter")
    obj_list = []
    for i in range(len(df_sample)):
        # print(df_sample.loc[i, "Title"])
        obj = make_dictionary(df_sample.loc[i, "Title"], df_sample.loc[i, "Cleaned_Ingredients"], df_sample.loc[i, "Image_Name"])
        obj_list.append(obj)
    
    return obj_list

